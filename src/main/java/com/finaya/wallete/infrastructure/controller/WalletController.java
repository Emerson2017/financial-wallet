package com.finaya.wallete.infrastructure.controller;

import com.finaya.wallete.application.usecase.wallet.*;
import com.finaya.wallete.infrastructure.dto.request.CreatePixKeyRequest;
import com.finaya.wallete.infrastructure.dto.request.CreateWalletRequest;
import com.finaya.wallete.infrastructure.dto.request.DepositWalletRequest;
import com.finaya.wallete.infrastructure.dto.request.WithdrawWalletRequest;
import com.finaya.wallete.infrastructure.dto.response.BalanceResponse;
import com.finaya.wallete.infrastructure.dto.response.CreatePixKeyResponse;
import com.finaya.wallete.infrastructure.dto.response.DepositWalletResponse;
import com.finaya.wallete.infrastructure.dto.response.WithdrawWalletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final CreateWalletUseCase createWalletUseCase;
    private final CreatePixKeyUseCase createPixKeyUseCase;
    private final GetWalletBalanceUseCase getWalletBalanceUseCase;
    private final DepositWalletUseCase depositWalletUseCase;
    private final WithdrawWalletUseCase withdrawWalletUseCase;


    public WalletController(CreateWalletUseCase createWalletUseCase,
                            CreatePixKeyUseCase createPixKeyUseCase,
                            GetWalletBalanceUseCase getWalletBalanceUseCase,
                            DepositWalletUseCase depositWalletUseCase,
                            WithdrawWalletUseCase withdrawWalletUseCase) {
        this.createWalletUseCase = createWalletUseCase;
        this.createPixKeyUseCase = createPixKeyUseCase;
        this.getWalletBalanceUseCase = getWalletBalanceUseCase;
        this.depositWalletUseCase = depositWalletUseCase;
        this.withdrawWalletUseCase = withdrawWalletUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createWallet(@RequestBody @Valid CreateWalletRequest request) {
        createWalletUseCase.execute(request.idUser());
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/pix-keys")
    public CreatePixKeyResponse createPixKey(@PathVariable Long id, @RequestBody @Valid CreatePixKeyRequest request) {
        return createPixKeyUseCase.execute(id, request);
    }

    @GetMapping("{id}/balance")
    public BalanceResponse getBalance(@PathVariable Long id,
                                      @RequestParam(required = false) Instant at) {
        return getWalletBalanceUseCase.execute(id, at);
    }


    @PostMapping("{id}/deposit")
    public DepositWalletResponse deposit(@PathVariable Long id, @RequestBody @Valid DepositWalletRequest request) {
        return depositWalletUseCase.execute(id, request);
    }

    @PostMapping("{id}/withdraw")
    public WithdrawWalletResponse withdraw(@PathVariable Long id, @RequestBody @Valid WithdrawWalletRequest request) {
        return withdrawWalletUseCase.execute(id, request);
    }
}
