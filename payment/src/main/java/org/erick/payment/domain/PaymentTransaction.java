package org.erick.payment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PaymentTransaction {
	private Long idUser;
	private String paymentName;
	private Long paymentCode;
}
