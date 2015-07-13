package com.anotherdev.stackapp.api;

import com.anotherdev.stackapp.app.DetailActivity;
import com.anotherdev.stackapp.app.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class
})
public interface ApiComponent {

    void inject(HomeActivity activity);
    void inject(DetailActivity activity);
}
