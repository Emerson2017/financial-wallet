package com.finaya.wallete.infrastructure.config;

import com.finaya.wallete.application.port.out.PixKeyRepositoryPort;
import com.finaya.wallete.application.port.out.PixTransactionRepositoryPort;
import com.finaya.wallete.application.port.out.WalletLedgerRepositoryPort;
import com.finaya.wallete.application.port.out.WalletRepositoryPort;
import com.finaya.wallete.application.usecase.pix.CreatePixTransferUseCase;
import com.finaya.wallete.application.usecase.pix.ProcessPixWebHookUseCase;
import com.finaya.wallete.application.usecase.wallet.*;
import com.finaya.wallete.infrastructure.mapper.PixKeyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateWalletUseCase createWalletUseCase(
            WalletRepositoryPort walletRepositoryPort) {

        return new CreateWalletUseCase(walletRepositoryPort);
    }

    @Bean
    public CreatePixKeyUseCase createPixKeyUseCase(
            PixKeyRepositoryPort pixKeyRepositoryPort,
            WalletRepositoryPort walletRepositoryPort,
            PixKeyMapper pixKeyMapper) {

        return new CreatePixKeyUseCase(pixKeyRepositoryPort,
                walletRepositoryPort,
                pixKeyMapper);
    }

    @Bean
    public GetWalletBalanceUseCase getWalletBalanceUseCase(
            WalletRepositoryPort walletRepositoryPort,
            WalletLedgerRepositoryPort walletLedgerRepositoryPort) {

        return new GetWalletBalanceUseCase(walletRepositoryPort, walletLedgerRepositoryPort);
    }

    @Bean
    public DepositWalletUseCase depositWalletUseCase(
            WalletRepositoryPort walletRepositoryPort,
            WalletLedgerRepositoryPort walletLedgerRepositoryPort) {

        return new DepositWalletUseCase(walletRepositoryPort, walletLedgerRepositoryPort);
    }

    @Bean
    public WithdrawWalletUseCase withdrawWalletUseCase(
            WalletRepositoryPort walletRepositoryPort,
            WalletLedgerRepositoryPort walletLedgerRepositoryPort) {

        return new WithdrawWalletUseCase(walletRepositoryPort, walletLedgerRepositoryPort);
    }

    @Bean
    public CreatePixTransferUseCase createPixTransferUseCase(
            PixTransactionRepositoryPort pixTransactionRepositoryPort,
            PixKeyRepositoryPort pixKeyRepositoryPort,
            WalletRepositoryPort walletRepositoryPort,
            WalletLedgerRepositoryPort walletLedgerRepositoryPort) {

        return new CreatePixTransferUseCase(pixTransactionRepositoryPort, walletLedgerRepositoryPort, walletRepositoryPort, pixKeyRepositoryPort);
    }

    @Bean
    public ProcessPixWebHookUseCase processPixWebHookUseCase(
            PixTransactionRepositoryPort pixTransactionRepositoryPort) {

        return new ProcessPixWebHookUseCase(pixTransactionRepositoryPort);
    }
}
