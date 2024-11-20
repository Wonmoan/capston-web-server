package com.example.alert;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    public void processAlert(Map<String, Object> alertData) {
        // 알림을 데이터베이스에 저장하거나, 클라이언트에 알림을 전송하는 로직 구현
        System.out.println("알림 처리: " + alertData);
    }
}
