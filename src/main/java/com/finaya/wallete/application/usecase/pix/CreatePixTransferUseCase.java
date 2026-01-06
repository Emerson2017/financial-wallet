package com.finaya.wallete.application.usecase.pix;

import com.finaya.wallete.application.port.out.PixKeyRepositoryPort;
import com.finaya.wallete.application.port.out.PixTransactionRepositoryPort;
import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.enums.CurrencyType;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.exception.EntityNotFoundException;
import com.finaya.wallete.domain.exception.InsufficientBalanceException;
import com.finaya.wallete.domain.model.PixKey;
import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.domain.model.WalletLedger;
import com.finaya.wallete.infrastructure.dto.request.CreatePixTransferRequest;
import com.finaya.wallete.infrastructure.dto.response.CreatePixTransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class CreatePixTransferUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreatePixTransferUseCase.class);

    private final PixTransactionRepositoryPort pixTransactionRepositoryPort;
    private final WalletLedgerRepositoryPort walletLedgerRepositoryPort;
    private final WalletRepositoryPort walletRepositoryPort;
    private final PixKeyRepositoryPort pixKeyRepositoryPort;

    public CreatePixTransferUseCase(PixTransactionRepositoryPort pixTransactionRepositoryPort,
                                    WalletLedgerRepositoryPort walletLedgerRepositoryPort,
                                    WalletRepositoryPort walletRepositoryPort,
                                    PixKeyRepositoryPort pixKeyRepositoryPort) {
        this.pixTransactionRepositoryPort = pixTransactionRepositoryPort;
        this.walletLedgerRepositoryPort = walletLedgerRepositoryPort;
        this.walletRepositoryPort = walletRepositoryPort;
        this.pixKeyRepositoryPort = pixKeyRepositoryPort;
    }


    @Transactional
    public CreatePixTransferResponse execute(CreatePixTransferRequest request, UUID idempotencyKey) throws InterruptedException {

        Optional<PixTransaction> pixTransactionOp = pixTransactionRepositoryPort
                .findByIdempotencyKey(idempotencyKey);

        if (pixTransactionOp.isPresent()) {
            return new CreatePixTransferResponse(pixTransactionOp.get());
        }

        Long fromWalletId = request.fromWalletId();
        String toPixKey = request.toPixKey();
        BigDecimal amount = request.amount();

        Wallet fromWallet = walletRepositoryPort.findByIdWithLock(fromWalletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet Not Found"));

        PixKey pixKey = pixKeyRepositoryPort.findByKey(toPixKey)
                .orElseThrow(() -> new EntityNotFoundException("Key Not Found"));

        Wallet toWallet = pixKey.getWallet();

        if (!canDebitWallet(fromWallet, amount)) {
            throw new InsufficientBalanceException();
        }

        WalletLedger ledger = fromWallet.debit(request.amount(), WalletMovementType.PIX_TRANSFER);
        walletLedgerRepositoryPort.save(ledger);

        PixTransaction pixTransaction = new PixTransaction(fromWallet, toWallet, idempotencyKey, toPixKey, amount, CurrencyType.BRL);
        pixTransaction = pixTransactionRepositoryPort.save(pixTransaction);

        logger.info("Pix transfer completed successfully: [Amount: {} | EndToEnd: {} | EventId: {} | IdempotencyId: {}]",
                request.amount(), pixTransaction.getEndToEndId(), ledger.getEventId(), pixTransaction.getIdempotencyKey());

        return new CreatePixTransferResponse(pixTransaction.getEndToEndId(), pixTransaction.getStatus());
    }

    private boolean canDebitWallet(Wallet wallet, BigDecimal amount) {
        BigDecimal balance = walletLedgerRepositoryPort
                .calculateBalance(wallet.getId(), Instant.now());

        return balance.compareTo(amount) >= 0;
    }
}
