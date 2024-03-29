package cn.milesians.module.lemon.authorization;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author milesians
 * @date 2023/4/13
 * @since 1.0
 */
@Entity(name = "Authorization")
@Table(name = "lemon_authorization")
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
public class Authorization implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String accessToken;

    private String refreshToken;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

    @EmbeddedId
    private AuthorizationId id;

    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class AuthorizationId implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        @Column(name = "book_code", nullable = false)
        private String bookCode;
        @Column(name = "app_type", nullable = false)
        private String appType;
    }
}


