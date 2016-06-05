package sample.security.authentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class SampleJdbcDaoImpl extends JdbcDaoImpl {

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] { username },
                new RowMapper<UserDetails>() {
                        public UserDetails mapRow(ResultSet rs, int rowNum)
                                        throws SQLException {
                                String loginId = rs.getString("LOGIN_ID");
                                String password = rs.getString("PASSWORD");
                                String fullName = rs.getString("FULL_NAME");
                                String deptName = rs.getString("DEPT_NAME");

                                SampleUser user = new SampleUser(loginId, password,
                                                AuthorityUtils.NO_AUTHORITIES);

                                user.setFullName(fullName);
                                user.setDeptName(deptName);

                                return user;
                        }
                });
    }

    @Override
    protected UserDetails createUserDetails(
            String username, UserDetails userFromUserQuery,
            List<GrantedAuthority> combinedAuthorities) {
        SampleUser origin = (SampleUser) userFromUserQuery;
        String loginId = origin.getUsername();
        String password = origin.getPassword();
        String fullName = origin.getFullName();
        String deptName = origin.getDeptName();

        SampleUser user = new SampleUser(loginId, password, combinedAuthorities);
        user.setFullName(fullName);
        user.setDeptName(deptName);

        return user;
    }
}
