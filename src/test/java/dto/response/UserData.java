package dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserData {
	@JsonProperty("id")
	private int id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("avatar")
	private String avatar;
}
