package com.example.jpaex.audit;

import com.example.jpaex.SpringJpaExampleApplication;
import com.example.jpaex.config.AuditorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {SpringJpaExampleApplication.class, AuditorConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void shouldCreatePayment() {
        //Given
        Payment payment = Payment.builder().amount(100.5f).build();

        //When
        Payment created = paymentRepository.save(payment);

        //Then
        assertThat(created.getAmount()).isEqualTo(payment.getAmount());
        assertThat(created.getCreatedBy()).isEqualTo("customer1234");
        assertThat(created.getCreated()).isBeforeOrEqualTo(now());
    }

}