package com.springinaction.tacocloud.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Slf4j
// if the annotation parameter value is omitted, then the table name
// is Taco_Order
@Table("Taco_Order")
public class TacoOrder implements Serializable {

  private static final long serialVersionUID = 12_432_133_454_676L;

  @Id
  private UUID id;

  private OffsetDateTime createdAt;

  // the default value of the Column annotation in this case
  // is "delivery_name
  @Column("delivery_name")
  @NotBlank(message = "Delivery name is required")
  private String deliveryName;

  @NotBlank(message = "Street is required")
  private String deliveryStreet;

  @NotBlank(message = "City is required")
  private String deliveryCity;

  @NotBlank(message = "State is required")
  private String deliveryState;

  @NotBlank(message = "Zip code is required")
  private String deliveryZip;

  @CreditCardNumber(message = "Not a valid card number")
  private String ccNumber;

  @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;

  //  @Size(min = 1, message = "There must be at least one taco in any order")
  private List<Taco> tacos = new ArrayList<>();

  public void addTaco(Taco taco) {
    log.debug("Adding a taco {} to list of tacos", taco);
    tacos.add(taco);
  }
}
