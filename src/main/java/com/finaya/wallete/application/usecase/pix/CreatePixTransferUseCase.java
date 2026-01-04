package com.finaya.wallete.application.usecase.pix;

import com.finaya.wallete.application.port.out.PixKeyRepositoryPort;
import com.finaya.wallete.application.port.out.PixTransactionRepositoryPort;
import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.enums.CurrencyType;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.model.PixKey;
import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.domain.model.WalletLedger;
import com.finaya.wallete.infrastructure.dto.request.CreatePixTransferRequest;
import com.finaya.wallete.infrastructure.dto.response.CreatePixTransferResponse;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class CreatePixTransferUseCase {

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
    public CreatePixTransferResponse execute(CreatePixTransferRequest request, UUID idempotencyKey) {

        Optional<PixTransaction> pixTransactionOp = pixTransactionRepositoryPort
                .findByIdempotencyKey(idempotencyKey);

        if (pixTransactionOp.isPresent()) {
            return new CreatePixTransferResponse(pixTransactionOp.get());
        }

        Long fromWalletId = request.fromWalletId();
        String toPixKey = request.toPixKey();
        BigDecimal amount = request.amount();

        Wallet fromWallet = walletRepositoryPort.findById(request.fromWalletId())
                .orElseThrow(() -> new RuntimeException("Wallet Not Found"));

        PixKey pixKey = pixKeyRepositoryPort.findByKey(toPixKey)
                .orElseThrow(() -> new RuntimeException("Key Not Found"));

        Long toWalletId = pixKey.getWalletId().getId();

        WalletLedger ledger = fromWallet.debit(request.amount(), WalletMovementType.PIX_TRANSFER);
        walletLedgerRepositoryPort.save(ledger);

        PixTransaction pixTransaction = new PixTransaction(fromWalletId, toWalletId, idempotencyKey, toPixKey, amount, CurrencyType.BRL);
        pixTransaction = pixTransactionRepositoryPort.save(pixTransaction);

        //todo: Integração com serviço PIX

        return new CreatePixTransferResponse(pixTransaction.getEndToEndId(), pixTransaction.getStatus());
    }
}
