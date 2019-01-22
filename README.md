	
# Assignment

A basic android application which uses news API to show headlines and articles live all over the web.

## The app is written in kotlin and uses:

* kotlin extensions.
* Kotlin extensions.
* AndroidX packages.

## The app has following packages:

* model- contains a data entity class which represents the data object for the response.
* network- Service classes that manages API calls.
* ui- main activity and fragments with a recyclerView adapter.
* viewModel- classes for storing and managing UI-ralated data.

## The app follows the Android Architectue Components desgin and uses:

* LiveData
* ViewModel

## Uses of third-party libraries in the app:

* Retrofit - managing network API calls.
* Gson - convertor to support serialization/deserialization.
* Picasso - image downloading and caching support.

## Common design patterns used in this app:

* Observer: It allows objects to be notified of any change of state of another object on which it depends. 
  presents in android viewModel
* Adapter: It allows two incompatible classes can work together. When creating a list of data, to inflate in a RecyclerView the Adapter   is used to send data from a controller to a particular view.
* ViewHolder: Used in the Adapters to reuse views in the inflation process of the cells. This brings us main benefit the proper    	   management of memory and fluency in lists. it's a mendatory in RecyclerView.
* Builder: Used to create customizeds objects in line, stacking calls. Present in the app during the creation of API service.

## Design Principles used are:

* DRY (Don’t Repeat Yourself)
* SRP: “Single Responsibility Principle” (part of SOLID principles)
