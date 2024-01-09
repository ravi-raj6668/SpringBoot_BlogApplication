//package io.innodev.blogapp.controller;
//
//import static org.mockito.Mockito.when;
//
//import io.innodev.blogapp.service.IUserService;
//
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {UserController.class})
//@ExtendWith(SpringExtension.class)
//class UserControllerDiffblueTest {
//    @MockBean
//    private IUserService iUserService;
//
//    @Autowired
//    private UserController userController;
//
//    /**
//     * Method under test: {@link UserController#getAllUser()}
//     */
//    @Test
//    void testGetAllUser() throws Exception {
//        when(iUserService.getAllUsers()).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/blog-app/users/getAllUsers");
//        MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//}
