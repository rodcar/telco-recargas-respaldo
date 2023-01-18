package pe.telco.recargas.messages.responses;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecargaHistorialResponse {
	private String numero;
	private Double monto;
	private Date fecha;
}
