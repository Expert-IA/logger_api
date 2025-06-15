package com.logger.logger_api.Application.Controller;

import com.logger.logger_api.Application.UseCase.DeleteUserTrackingById;
import com.logger.logger_api.Application.UseCase.GetUserTrackingByUserIdUseCase;
import com.logger.logger_api.Application.UseCase.SaveUserTrackingUseCase;
import com.logger.logger_api.Application.Dtos.UserTrackingDTO;
import com.logger.logger_api.Application.UseCase.UpdateUserTrackingByIdUseCase;
import com.logger.logger_api.Domain.Entity.UserTracking;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Tracking", description = "APIs para gerenciar User Tracking")
@RestController
@RequestMapping("/userTracking")
public class LoggerController {

    private final SaveUserTrackingUseCase saveUserTrackingUseCase;
    private final GetUserTrackingByUserIdUseCase getUserTrackingByUserIdUseCase;
    private final DeleteUserTrackingById deleteUserTrackingById;
    private final UpdateUserTrackingByIdUseCase updateUserTrackingByIdUseCase;

    public LoggerController(
            SaveUserTrackingUseCase saveUserTrackingUseCase,
            GetUserTrackingByUserIdUseCase getUserTrackingByUserIdUseCase,
            DeleteUserTrackingById deleteUserTrackingById,
            UpdateUserTrackingByIdUseCase updateUserTrackingByIdUseCase
    ) {
        this.saveUserTrackingUseCase = saveUserTrackingUseCase;
        this.getUserTrackingByUserIdUseCase = getUserTrackingByUserIdUseCase;
        this.deleteUserTrackingById = deleteUserTrackingById;
        this.updateUserTrackingByIdUseCase = updateUserTrackingByIdUseCase;
    }

    @Operation(summary = "Health check", description = "Verifica se a API está rodando")
    @GetMapping
    public String healthCheck() {
        return "Ok";
    }

    @Operation(summary = "Salva um UserTracking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "UserTracking criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar")
    })
    @PostMapping("/save")
    public ResponseEntity<Void> saveTracking(
            @Parameter(description = "Dados do UserTracking para salvar", required = true)
            @RequestBody UserTrackingDTO dto) {
        saveUserTrackingUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Busca UserTracking pelo ID do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de UserTracking retornada"),
            @ApiResponse(responseCode = "404", description = "UserTracking não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar usuário")
    })
    @GetMapping("/userTracking/get")
    public UserTracking getById(
            @RequestParam String id) {
        return getUserTrackingByUserIdUseCase.execute(id);
    }


    @Operation(summary = "Deleta UserTracking pelo ID do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserTracking deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar UserTracking")
    })
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserTracking(
            @Parameter(description = "ID do usuário a ser deletado", required = true)
            @PathVariable String userId) {
            deleteUserTrackingById.execute(userId);
            return ResponseEntity.ok("User with ID " + userId + " was successfully deleted.");
    }

    @Operation(summary = "Atualiza UserTracking pelo ID do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserTracking atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar")
    })
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUserTracking(
            @Parameter(description = "ID do usuário para atualizar", required = true)
            @PathVariable String userId,
            @Parameter(description = "Dados atualizados do UserTracking", required = true)
            @RequestBody UserTrackingDTO updatedTrackingDTO) {
        updateUserTrackingByIdUseCase.execute(userId, updatedTrackingDTO);
        return ResponseEntity.ok("UserTracking with ID " + userId + " was updated.");
    }
}
