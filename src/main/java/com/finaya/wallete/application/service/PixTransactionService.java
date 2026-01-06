package com.finaya.wallete.application.service;

import com.finaya.wallete.application.port.out.PixTransactionRepositoryPort;
import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.exception.EntityNotFoundException;
import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.domain.model.WalletLedger;
import org.springframework.transaction.annotation.Transactional;

public class PixTransactionService {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletLedgerRepositoryPort walletLedgerRepositoryPort;
    private final PixTransactionRepositoryPort pixTransactionRepositoryPort;

    public PixTransactionService(WalletRepositoryPort walletRepositoryPort,
                                 WalletLedgerRepositoryPort walletLedgerRepositoryPort,
                                 PixTransactionRepositoryPort pixTransactionRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletLedgerRepositoryPort = walletLedgerRepositoryPort;
        this.pixTransactionRepositoryPort = pixTransactionRepositoryPort;
    }

    @Transactional
    public void confirm(PixTransaction pixTransaction) {
        Wallet toWallet = walletRepositoryPort.findByIdWithLock(pixTransaction.getToWallet().getId())
                .orElseThrow(() -> new EntityNotFoundException("Wallet Not Found"));

        WalletLedger ledger = toWallet.credit(pixTransaction.getAmount(), WalletMovementType.PIX_TRANSFER);
        walletLedgerRepositoryPort.save(ledger);

        pixTransactionRepositoryPort.save(pixTransaction.confirm());
    }

    @Transactional
    public void reject(PixTransaction pixTransaction) {
        Wallet fromWallet = walletRepositoryPort.findByIdWithLock(pixTransaction.getFromWallet().getId())
                .orElseThrow(() -> new EntityNotFoundException("Wallet Not Found"));;

        WalletLedger ledger = fromWallet.credit(pixTransaction.getAmount(), WalletMovementType.PIX_TRANSFER_CANCELLED);
        walletLedgerRepositoryPort.save(ledger);

        pixTransactionRepositoryPort.save(pixTransaction.reject());
    }
}
