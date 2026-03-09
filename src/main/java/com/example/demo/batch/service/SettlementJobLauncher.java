package com.example.demo.batch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SettlementJobLauncher {

    private final JobOperator jobOperator;
    private final Job settlementTaskletJob;
    private final Job settlementChunkJob;

    public JobExecution launch(LocalDate settlementDate) throws Exception {
        return launchTasklet(settlementDate);
    }

    public JobExecution launchTasklet(LocalDate settlementDate) throws Exception {
        return jobOperator.start(settlementTaskletJob, buildJobParameters(settlementDate, "tasklet"));
    }

    public JobExecution launchChunk(LocalDate settlementDate) throws Exception {
        return jobOperator.start(settlementChunkJob, buildJobParameters(settlementDate, "chunk"));
    }

    private JobParameters buildJobParameters(LocalDate settlementDate, String mode) {
        return new JobParametersBuilder()
                .addString("settlementDate", settlementDate.toString())
                .addString("mode", mode)
                // 동일 날짜 재실행을 위한 유니크 파라미터
                .addLong("requestedAt", System.currentTimeMillis())
                .toJobParameters();
    }
}