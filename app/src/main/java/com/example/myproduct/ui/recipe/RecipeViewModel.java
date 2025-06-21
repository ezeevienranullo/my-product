package com.example.myproduct.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myproduct.model.Product;
import com.example.myproduct.model.ProductResponse;
import com.example.myproduct.model.Recipe;
import com.example.myproduct.model.RecipeResponse;
import com.example.myproduct.service.ApiClient;
import com.example.myproduct.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<List<Recipe>> recipeList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public RecipeViewModel() {
        fetchRecipes(); // optional: auto-fetch on init
    }

    public LiveData<List<Recipe>> getRecipeList() {
        return recipeList;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchRecipes() {
        isLoading.setValue(true);
        ApiService apiService = ApiClient.getApiService();
        apiService.getRecipes().enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    recipeList.setValue(response.body().getRecipes());
                } else {
                    errorMessage.setValue("Failed to load products");
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue(t.getMessage());
            }
        });
    }
}