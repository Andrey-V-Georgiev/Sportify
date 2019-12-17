package com.softuni.sportify.servicesTesting;

import com.softuni.sportify.domain.models.service_models.RoleServiceModel;
import com.softuni.sportify.repositories.RoleRepository;
import com.softuni.sportify.services.RoleService;
import com.softuni.sportify.services.RoleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.softuni.sportify.constants.RoleConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleServiceTests {

    @Autowired
    private RoleRepository roleRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private RoleService roleService;

    @Before
    public void init() {
        this.roleService = new RoleServiceImpl(this.modelMapper, roleRepository);
    }

    /*  void initialRolesSeed() */
    @Test
    public void initialRolesSeed_whenEmptyDb_successfulSeed()  {

        int initialSize = this.roleRepository.findAll().size();
        this.roleService.initialRolesSeed();
        int afterSeedSize = this.roleRepository.findAll().size();

        assertTrue(initialSize != afterSeedSize);
    }


    /*   Set<RoleServiceModel> findAllRoles()  */
    @Test
    public void findAllRoles_whenSeededDb_correctResult()  {

        int initialSize = this.roleRepository.findAll().size();
        this.roleService.initialRolesSeed();
        int expected = 3;
        int actual = this.roleRepository.findAll().size();

        assertEquals(expected, actual);
    }


    /*  RoleServiceModel findByAuthority(String authority) */
    @Test
    public void findByAuthority_whenAuthorityIsCorrect_correctResult()  {

        this.roleService.initialRolesSeed();
        String userRoleStr = ROLE_USER;
        String userAdminStr = ROLE_ADMIN;
        String userRootStr = ROLE_ROOT;
        RoleServiceModel roleUser = this.roleService.findByAuthority(ROLE_USER);
        RoleServiceModel roleAdmin = this.roleService.findByAuthority(ROLE_ADMIN);
        RoleServiceModel roleRoot = this.roleService.findByAuthority(ROLE_ROOT);

        assertEquals(userRoleStr, roleUser.getAuthority());
        assertEquals(userAdminStr, roleAdmin.getAuthority());
        assertEquals(userRootStr, roleRoot.getAuthority());
    }
}
