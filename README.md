Plateful Android App
Overview
Plateful is an Android application built with a clean layered architecture to explore meals from TheMealDB API.
The app allows users to:
- Search meals by name.
- Filter meals by category, country, or ingredient.
- View detailed meal information including instructions, ingredients, and media.
- Save meals locally as favorites using Room database.

Architecture
The project follows a strict layered architecture with no modification of existing components. All new features are added additively.
Layers
- Network Layer
- MealsService: Retrofit interface for TheMealDB endpoints.
- RetrofitClient: Provides Retrofit instance.
- Remote Data Source
- RemoteDataSource: Wraps MealsService calls and returns RxJava Single responses.
- Response wrappers: MealsResponse, CategoriesResponse, CountriesResponse, IngredientsResponse.
- Local Data Source
- MealDao: Room DAO for CRUD operations.
- AppDataBase: Room database singleton.
- LocalDataSource: Provides access to local persistence.
- Repository
- MealsRepository: Mediates between remote and local data sources.
- Model
- MealDto: Represents meal data from API.
- MealEntity: Represents meal data stored locally.
- CategoryDto, CountryDto, IngredientDto: DTOs for filter options.
- Presenter
- SearchPresenter: Handles business logic, connects repository with view, manages RxJava subscriptions.
- View
- SearchMealsFragment: Displays search bar, chips for filters, RecyclerView for meals.
- SearchMealsAdapter: RecyclerView adapter for displaying meals.
- SearchMealsView: Interface defining view contract.

Features
- Search Meals: Debounced search using RxJava PublishSubject.
- Filter Meals: Chips trigger dialogs listing categories, countries, or ingredients.
- Meal Details: Navigation to details fragment with full recipe information.
- Favorites: Meals can be saved locally using Room.

API Endpoints
- search.php?s={mealName} → Search meals by name.
- lookup.php?i={mealID} → Get meal details.
- filter.php?c={category} → Filter by category.
- filter.php?a={country} → Filter by country.
- filter.php?i={ingredient} → Filter by ingredient.
- list.php?c=list → List all categories.
- list.php?a=list → List all countries.
- list.php?i=list → List all ingredients.

Tech Stack
- Language: Java
- Architecture: Layered (Network → DataSource → Repository → Presenter → View)
- Networking: Retrofit
- Reactive Programming: RxJava3
- Persistence: Room Database
- UI: RecyclerView, Chips, Dialogs, Navigation Component

Error Handling
- Remote calls use onErrorReturnItem to provide safe fallbacks.
- RxJava subscriptions include onError handlers to prevent OnErrorNotImplementedException.
- Room entities enforce NOT NULL constraints; mappers ensure safe defaults.

Setup
- Clone the repository.
- Configure Retrofit base URL to https://www.themealdb.com/api/json/v1/1/.
- Build and run the app on Android Studio.
