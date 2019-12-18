package com.softuni.sportify.servicesTesting;
import com.softuni.sportify.domain.entities.Role;
import com.softuni.sportify.domain.entities.User;
import com.softuni.sportify.domain.models.service_models.RoleServiceModel;
import com.softuni.sportify.domain.models.service_models.UserServiceModel;
import com.softuni.sportify.exceptions.CreateException;
import com.softuni.sportify.exceptions.DeleteException;
import com.softuni.sportify.exceptions.ReadException;
import com.softuni.sportify.exceptions.UpdateException;
import com.softuni.sportify.repositories.RoleRepository;
import com.softuni.sportify.repositories.UserRepository;
import com.softuni.sportify.services.RoleService;
import com.softuni.sportify.services.UserService;
import com.softuni.sportify.services.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;

import java.util.HashSet;
import java.util.Set;

import static com.softuni.sportify.constants.RoleConstants.*;
import static com.softuni.sportify.factory.RoleFactory.createAdminRoleServiceModel;
import static com.softuni.sportify.factory.RoleFactory.createUserRoleServiceModel;
import static com.softuni.sportify.factory.UserFactory.createValidUserServiceModel;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    private RoleService roleService;
    @MockBean
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private UserServiceModel validUserServiceModel1;
    private RoleServiceModel userRoleServiceModel2;
    private RoleServiceModel adminRoleServiceModel3;

    @Before
    public void init() {
        this.userService = new UserServiceImpl(
                this.modelMapper, this.bCryptPasswordEncoder,
                this.userRepository, this.roleService, this.validator);

        /* CREATE VALID USER SERVICE MODEL 1 */
        validUserServiceModel1 = createValidUserServiceModel();

        /* CREATE <user> ROLE SERVICE MODEL 2 */
        userRoleServiceModel2 = createUserRoleServiceModel();

        /* CREATE <admin> ROLE SERVICE MODEL 3 */
        adminRoleServiceModel3 = createAdminRoleServiceModel();
    }

    /*  UserServiceModel registerUser(UserServiceModel userServiceModel)  */

    @Test(expected = Exception.class)
    public void registerUser_whenModelIsNotValid_throwsException() throws CreateException {

        validUserServiceModel1.setUsername("");
        UserServiceModel userServiceModel = this.userService.registerUser(validUserServiceModel1);
    }

    /*  List<UserServiceModel> findAllUsers() */
    @Test
    public void findAllUsers_whenDbSize1_successfully()  {

        User user = this.userRepository.saveAndFlush(this.modelMapper.map(validUserServiceModel1, User.class));
        int expected = 1;
        int actual = this.userService.findAllUsers().size();

        assertEquals(expected, actual);
    }

    /* void deleteUser(String id)  */
    @Test
    public void deleteUser_whenInputIsValid_successfully() throws DeleteException {

        User user = this.userRepository.saveAndFlush(this.modelMapper.map(validUserServiceModel1, User.class));
        int initialSize = this.userService.findAllUsers().size();
        this.userService.deleteUser(user.getId());
        int updatedSize = this.userService.findAllUsers().size();
        assertNotEquals(initialSize, updatedSize);
    }

    @Test(expected = Exception.class)
    public void deleteUser_whenInputIsNotValid_throwsException() throws DeleteException {

        this.userService.deleteUser("wrongId");
    }

    /*  UserServiceModel findById(String id) */
    @Test
    public void findById_whenInputIsValid_successfully() throws ReadException {

        User user = this.userRepository.saveAndFlush(this.modelMapper.map(validUserServiceModel1, User.class));
        UserServiceModel userServiceModel = this.userService.findById(user.getId());
        assertEquals(user.getId(), userServiceModel.getId());
    }

    @Test(expected = Exception.class)
    public void findById_whenInputIsNotValid_throwsException() throws ReadException {

        UserServiceModel userServiceModel = this.userService.findById("wrongId");
    }

    /*  UserServiceModel changeUserAuthorities(String id, String authority) */

    @Test(expected = Exception.class)
    public void changeUserAuthorities_whenInputIsNotValid_throwsException() throws ReadException, UpdateException {

        Role userRole = this.roleRepository.saveAndFlush(this.modelMapper.map(userRoleServiceModel2, Role.class));
        Set<RoleServiceModel> userRoleSet = new HashSet<>();
        userRoleSet.add(this.modelMapper.map(userRole, RoleServiceModel.class));
        validUserServiceModel1.setAuthorities(userRoleSet);
        User user = this.userRepository.saveAndFlush(this.modelMapper.map(validUserServiceModel1, User.class));
        this.userService.changeUserAuthorities(user.getId(), "wrongRole");
    }

}
