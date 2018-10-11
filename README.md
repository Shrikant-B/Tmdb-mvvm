# TMDb-mvvm
The Movie Database implementation using Model-View-ViewModel architecture with dagger2 &amp; rxjava

## The Model-View-ViewModel Pattern
MVVM is one of the architectural patterns which enhances separation of concerns, it allows separating the user interface logic from the business (or the back-end) logic. Its target (with other MVC patterns goal) is to achieve the following principle “Keeping UI code simple and free of app logic in order to make it easier to manage”.

The main players in the MVVM pattern are:

* Model
Model represents the data and business logic of the app. One of the recommended implementation strategies of this layer, is to expose its data through observables to be decoupled completely from ViewModel or any other observer/consumer.
* ViewModel
ViewModel interacts with model and also prepares observable(s) that can be observed by a View. ViewModel can optionally provide hooks for the view to pass events to the model. 
One of the important implementation strategies of this layer is to decouple it from the View, i.e, ViewModel should not be aware about the view who is interacting with.
*View
Finally, the view role in this pattern is to observe (or subscribe to) a ViewModel observable to get data in order to update UI elements accordingly.

The following diagram shows MVVM components and basic interactions.

<img src="https://cdn-images-1.medium.com/max/900/1*BpxMFh7DdX0_hqX6ABkDgw.png" alt="Model View ViewModel"/>

### Stable samples - Java
| Sample | Description |
| ------------- | ------------- |
| [mvvm-rxjava](https://github.com/Shrikant-B/Tmdb-mvvm/tree/mvvm-rxjava) | Demonstrates a basic [Model‑View‑ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture with Data Binding library. This sample uses [RxJava 2](https://github.com/ReactiveX/RxJava) to implement concurrency, and abstract the data layer. |
| [mvvm-livedata](https://github.com/Shrikant-B/Tmdb-mvvm/tree/mvvm-livedata) | Demonstrates a basic [Model‑View‑ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture with Data Binding library. This sample uses [Live data](https://developer.android.com/topic/libraries/architecture/livedata). |

## References
[MVVM architecture, ViewModel and LiveData](https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1)
[Android Architecture Patterns: Model-View-ViewModel](https://medium.com/upday-devs/android-architecture-patterns-part-3-model-view-viewmodel-e7eeee76b73b)