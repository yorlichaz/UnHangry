package com.example.hunger.ui.recipe_gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hunger.R;
import com.example.hunger.databinding.FragmentRecipeGalleryBinding;
import com.example.hunger.models.Recipe;


import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecipeGalleryFragment extends Fragment {

    private RecipeViewModel recipeVm;

    private FragmentRecipeGalleryBinding recipeGalleryBinding =  null;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeGalleryBinding = FragmentRecipeGalleryBinding.bind(view);
        recipeVm = new ViewModelProvider(this).get(RecipeViewModel.class);

        RecyclerView recyclerView = recipeGalleryBinding.galleryRecycleView;
        RecipeGalleryAdapter adapter = new RecipeGalleryAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        LiveData<List<Recipe>> randomRecipes = recipeVm.getRandomRecipes();
        recipeVm.getRandomRecipes().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recipeGalleryBinding = FragmentRecipeGalleryBinding.inflate(inflater,container,false);

        return recipeGalleryBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.gallery_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.gallery_menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query != null){

                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do nothing on query change
                return true;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recipeGalleryBinding = null;
    }
}
