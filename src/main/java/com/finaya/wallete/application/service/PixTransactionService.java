package com.finaya.wallete.application.service;

import com.finaya.wallete.application.port.out.PixTransactionRepositoryPort;
import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.domain.enums.WalletMovementType;
import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.domain.model.Wallet;
import com.finaya.wallete.domain.model.WalletLedger;
import org.springframework.transaction.annotation.Transactional;

public class PixTransactionService {

    private final WalletLedgerRepositoryPort walletLedgerRepositoryPort;
    private final PixTransactionRepositoryPort pixTransactionRepositoryPort;

    public PixTransactionService(WalletLedgerRepositoryPort walletLedgerRepositoryPort,
                                 PixTransactionRepositoryPort pixTransactionRepositoryPort) {
        this.walletLedgerRepositoryPort = walletLedgerRepositoryPort;
        this.pixTransactionRepositoryPort = pixTransactionRepositoryPort;
    }

    @Transactional
    public void confirm(PixTransaction pixTransaction) {
        Wallet toWallet = pixTransaction.getToWallet();

        WalletLedger ledger = toWallet.credit(pixTransaction.getAmount(), WalletMovementType.PIX_TRANSFER);
        walletLedgerRepositoryPort.save(ledger);

        pixTransactionRepositoryPort.save(pixTransaction.confirm());
    }

    @Transactional
    public void reject(PixTransaction pixTransaction) {
        Wallet fromWallet = pixTransaction.getFromWallet();

        WalletLedger ledger = fromWallet.credit(pixTransaction.getAmount(), WalletMovementType.PIX_TRANSFER_CANCELLED);
        walletLedgerRepositoryPort.save(ledger);

        pixTransactionRepositoryPort.save(pixTransaction.reject());
    }
}
