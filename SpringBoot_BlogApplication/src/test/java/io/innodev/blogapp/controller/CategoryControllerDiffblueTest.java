//package io.innodev.blogapp.controller;
//
//import static org.mockito.Mockito.when;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.innodev.blogapp.payloads.CategoryDTO;
//import io.innodev.blogapp.service.CategoryService;
//
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {CategoryController.class})
//@ExtendWith(SpringExtension.class)
//class CategoryControllerDiffblueTest {
//    @Autowired
//    private CategoryController categoryController;
//
//    @MockBean
//    private CategoryService categoryService;
//
//    /**
//     * Method under test: {@link CategoryController#createCategory(CategoryDTO)}
//     */
//    @Test
//    void testCreateCategory() throws Exception {
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setCategoryDescription("Category Description");
//        categoryDTO.setCategoryId(1);
//        categoryDTO.setCategoryTitle("Dr");
//        String content = (new ObjectMapper()).writeValueAsString(categoryDTO);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/category/createCategory")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
//    }
//
//    /**
//     * Method under test: {@link CategoryController#createCategory(CategoryDTO)}
//     */
//    @Test
//    void testCreateCategory2() throws Exception {
//        when(categoryService.createCategory(Mockito.<CategoryDTO>any())).thenReturn(new CategoryDTO());
//
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setCategoryDescription("Category Description");
//        categoryDTO.setCategoryId(1);
//        categoryDTO.setCategoryTitle("Prof");
//        String content = (new ObjectMapper()).writeValueAsString(categoryDTO);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/category/createCategory")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("{\"categoryId\":null,\"categoryTitle\":null,\"categoryDescription\":null}"));
//    }
//
//    /**
//     * Method under test:
//     * {@link CategoryController#updateCategory(CategoryDTO, Integer)}
//     */
//    @Test
//    void testUpdateCategory2() throws Exception {
//        when(categoryService.updateCategory(Mockito.<CategoryDTO>any(), Mockito.<Integer>any()))
//                .thenReturn(new CategoryDTO());
//
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setCategoryDescription("Category Description");
//        categoryDTO.setCategoryId(1);
//        categoryDTO.setCategoryTitle("Prof");
//        String content = (new ObjectMapper()).writeValueAsString(categoryDTO);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .put("/api/v1/category/updateCategory/{categoryId}", 1)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(categoryController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("{\"categoryId\":null,\"categoryTitle\":null,\"categoryDescription\":null}"));
//    }
//
//    /**
//     * Method under test: {@link CategoryController#getCategory(Integer)}
//     */
//    @Test
//    void testGetCategory() throws Exception {
//        when(categoryService.getCategory(Mockito.<Integer>any())).thenReturn(new CategoryDTO());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/api/v1/category/getCategory/{categoryId}", 1);
//        MockMvcBuilders.standaloneSetup(categoryController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("{\"categoryId\":null,\"categoryTitle\":null,\"categoryDescription\":null}"));
//    }
//
//    /**
//     * Method under test: {@link CategoryController#deleteCategory(Integer)}
//     */
//    @Test
//    void testDeleteCategory() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/api/v1/category/deleteCategory/{categoryId}", 1);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
//    }
//
//    /**
//     * Method under test: {@link CategoryController#getCategoryList()}
//     */
//    @Test
//    void testGetCategoryList() throws Exception {
//        when(categoryService.getAllCatrgoryList()).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/category/getAllCategory");
//        MockMvcBuilders.standaloneSetup(categoryController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//
//    /**
//     * Method under test:
//     * {@link CategoryController#updateCategory(CategoryDTO, Integer)}
//     */
//    @Test
//    void testUpdateCategory() throws Exception {
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setCategoryDescription("Category Description");
//        categoryDTO.setCategoryId(1);
//        categoryDTO.setCategoryTitle("Dr");
//        String content = (new ObjectMapper()).writeValueAsString(categoryDTO);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .put("/api/v1/category/updateCategory/{categoryId}", 1)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
//    }
//}
