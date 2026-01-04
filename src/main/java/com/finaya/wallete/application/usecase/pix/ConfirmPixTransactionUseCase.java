package com.finaya.wallete.application.usecase.pix;

import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.domain.model.PixTransaction;
import com.finaya.wallete.domain.model.Wallet;

public class ConfirmPixTransactionUseCase {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletLedgerRepositoryPort walletLedgerRepositoryPort;

    public ConfirmPixTransactionUseCase(WalletRepositoryPort walletRepositoryPort,
                                        WalletLedgerRepositoryPort walletLedgerRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletLedgerRepositoryPort = walletLedgerRepositoryPort;
    }

    public PixTransaction execute(PixTransaction pixTransaction) {
        Wallet toWallet = pixTransaction.getToWalletId();
    }
}
