package cool.leeson.library.entity.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {
    private String access_token;
    private Long expires_in;

}
