package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    private RecipeServiceImpl recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {

        // Given
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipeData);

        // When
        Set<Recipe> recipes = recipeService.getRecipes();

        // Then
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}