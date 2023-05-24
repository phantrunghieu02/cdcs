package pl.markowski.kinoteatr.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", nullable = false)
    @Size(min = 4, message = "Thông tin đăng nhập phải có ít nhất 4 ký tự")
    @Size(max = 50, message = "Thông tin đăng nhập có thể có tối đa 50 ký tự")
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 6, message = "Mật khẩu phải dài ít nhất 6 ký tự")
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "Hãy nhập địa chỉ email của bạn")
    @Email(message = "Vui lòng nhập địa chỉ email hợp lệ")
    private String email;

    @Column(name = "phone", nullable = false)
    @Pattern(regexp = "\\d{10}", message = "Vui lòng nhập 10 chữ số trong số điện thoại của bạn")
    private String phone;
    // đã sửa sang 10
    @Column(name = "name", nullable = false)
    @Size(max = 50, message = "Nhập tối đa 50 ký tự")
    @NotBlank(message = "Hãy nhập tên của bạn")
    private String name;

    @Column(name = "surname", nullable = false)
    @Size(max = 50, message = "Nhập tối đa 50 ký tự")
    @NotBlank(message = "Hãy nhập họ của bạn")
    private String surname;

    @Column(name = "active", nullable = false)
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}