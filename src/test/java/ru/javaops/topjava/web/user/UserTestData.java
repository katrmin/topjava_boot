package ru.javaops.topjava.web.user;

import ru.javaops.topjava.model.Role;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.model.UserRole;
import ru.javaops.topjava.util.JsonUtil;
import ru.javaops.topjava.web.MatcherFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "roles.id", "password");
    public static MatcherFactory.Matcher<User> USER_WITH_MEALS_MATCHER =
            MatcherFactory.usingAssertions(User.class,
                    //     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final Long USER_ID = 1L;
    public static final Long ADMIN_ID = 2L;
    public static final int GUEST_ID = 3;
    public static final int NOT_FOUND = 100;
    public static final String USER_MAIL = "user@yandex.ru";
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String GUEST_MAIL = "guest@gmail.com";

    //    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User admin = User.builder()
            .id(ADMIN_ID)
            .name("Admin")
            .email(ADMIN_MAIL)
            .password("admin")
            .roles(Set.of(UserRole.builder().role(Role.ADMIN).build(), UserRole.builder().role(Role.USER).build()))
            .build();
//    public static final User guest = new User(GUEST_ID, "Guest", GUEST_MAIL, "guest" );

//    static {
//        user.setMeals(meals);
//        admin.setMeals(List.of(adminMeal2, adminMeal1));
//    }

//    public static User getNew() {
//        return new User(null, "New", "new@gmail.com", "newPass",  false, new Date(), Collections.singleton(Role.USER));
//    }

    public static User getUpdated() {
        return User.builder()
                .id(USER_ID)
                .name("UpdatedName")
                .email(USER_MAIL)
                .password("newPass")
//                .roles(Set.of(UserRole.builder().role(Role.ADMIN).build()))
                .build();
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
