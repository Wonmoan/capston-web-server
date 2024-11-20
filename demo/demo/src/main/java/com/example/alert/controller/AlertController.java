package com.example.alert.controller;

import com.example.alert.repository.AlertRepository;
import com.example.alert.service.AlertService;
import com.example.alert.domain.Alert;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;  // java.util.List를 임포트
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AlertController {

    private final AlertRepository alertRepository;
    private final AlertService alertService;

    public AlertController(AlertRepository alertRepository, AlertService alertService) {
        this.alertRepository = alertRepository;
        this.alertService = alertService;
    }

    @GetMapping("/alerts")
    public List<Alert> getAlerts() {
        return alertRepository.findAll();
    }

    @PostMapping("/alert")
    public ResponseEntity<String> receiveAlert(@RequestBody Map<String, Object> payload) {
        String message = payload.get("message").toString();
        Alert alert = new Alert();
        alert.setMessage(message);
        alert.setTimestamp(LocalDateTime.now());
        alertRepository.save(alert);

        // 클라이언트에 실시간 알림 보내기 (예: WebSocket 사용)
        // 알림 로직 추가
        alertService.processAlert(payload);

        return ResponseEntity.ok("Alert received and saved");
    }
}
