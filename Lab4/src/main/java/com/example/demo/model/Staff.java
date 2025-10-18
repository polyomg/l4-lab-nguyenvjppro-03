package com.example.demo.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
    @NotBlank(message = "Chưa nhập email")
    @Email(message = "Email không đúng định dạng")
    private String id;

    @NotBlank(message = "Chưa nhập họ và tên")
    private String fullname;

    @Builder.Default
    private String photo = "photo.jpg";

    @NotNull(message = "Chưa chọn giới tính")
    private Boolean gender;

    @NotNull(message = "Chưa nhập ngày sinh")
    @Past(message = "Ngày sinh không hợp lệ")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

    @Min(value = 1000, message = "Lương tối thiểu phải là 1000")
    @Max(value = 10000, message = "Lương tối đa là 10000" )
    @NotNull(message = "Chưa nhập lương")
    private Double salary;

    private Integer level;
}
