Android Architecture Components Basic Sample with MVVM and RxJava
===================================================================

This sample showcases the following Architecture Components:

* [ViewModels](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)
* [DataBinding](https://developer.android.com/topic/libraries/data-binding/index.html)

Introduction
-------------

### Features

This sample contains two screens: a list of github repositories and detail view, that shows the detail of the repository.
Using datatbinding apis to implement MVVM Architectural pattern.

#### View Layer
* HomeActivity: A main activity that handles navigation between the fragmentsand encapsulates the common UI piece of code.
* A fragment to display the list of github repositories.
* A fragment to display a repository detail(The details has been encapsulated in a Parcelize object and shared across)

#### Presentation layer
* A ViewModel

The app uses a Model-View-ViewModel (MVVM) architecture for the presentation layer. Each of the fragments corresponds to a MVVM View. The View and ViewModel communicate  using LiveData and the following design principles:

* ViewModel objects don't have references to activities, fragments, or Android views. That would cause leaks on configuration changes, such as a screen rotation, because the system retains a ViewModel across the entire lifecycle of the corresponding view.

* ViewModel objects expose data using `Observable` objects to UI. It talks to Model to observe and get the data changes.

* Views, including the fragments used in this sample, subscribe to corresponding `Obsservable` objects using RxJava. This is an example of a subscription:

```java
        // Update the list of githug repos when the underlying data changes.
        disposable = viewModel.getReposObservable().observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)
```

#### Data layer

* Service APIs(GithubApi), that returns the data in json format on network service call.
* NetworkModule provides the RetrofitClient object to make api calls. Also provide the GithubApi.
* Repository and Repo Model/Data/Bean Classes

#### Dagger
AppComponent is the Dagger Component for Dependency injection framework
Module: NetworkModule, AppModule

#### UnitTesting
*GithubRepositoryListViewModelTest : Junit for ViewModel
*Instrumentation test cases can be written using Espress



License
--------

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.



