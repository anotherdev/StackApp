package com.anotherdev.stackapp.api;

import com.anotherdev.stackapp.api.stackexchange.StackOverflowApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class
})
public interface ApiComponent {

    StackOverflowApi stackoverflow();
}
