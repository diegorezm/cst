package com.app.cst.domain.Transactions;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record ReportDTO(

          @NotNull(message = "Start date is required") Date start,

          @NotNull(message = "End date is required") Date end) {

}
