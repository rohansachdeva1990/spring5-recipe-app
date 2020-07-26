package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.sound.midi.Receiver;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @InjectMocks
    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        //indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() throws Exception {

        // Given
        Set<Recipe> recipes = new HashSet<>();

        Recipe recipe1 = new Recipe();
        recipe1.setId(1l);
        recipes.add(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setId(2l);
        recipes.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // When
        String viewName = indexController.getIndexPage(model);

        // Then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"),
                argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}