# FancyGallery App

The **FancyGallery App** is a dynamic Android application that fetches and displays curated images from Flickr. It offers an interactive experience where users can explore images in a grid view or view them on a world map with custom photo markers. The app seamlessly integrates modern Android components to provide smooth navigation and engaging features.

---

<div style="display: flex; justify-content: center; align-items: center; gap: 10px;">
  <img src="https://github.com/chhsch/FancyGallery/blob/18f00dcb053886d49f2e41e33831fda70a3f9c12/img/Screenshot_20250112_133411.png" alt="Gallery View" width="200"/>
  <img src="https://github.com/chhsch/FancyGallery/blob/18f00dcb053886d49f2e41e33831fda70a3f9c12/img/Screenshot_20250112_133507.png" alt="Map View" width="200"/>
  <img src="https://github.com/chhsch/FancyGallery/blob/18f00dcb053886d49f2e41e33831fda70a3f9c12/img/Screenshot_20250112_133520.png" alt="Map View" width="200"/>
</div>

## 📱 Features at a Glance

### Interactive Gallery & Map Views
- Browse photos in a smooth scrolling grid layout.  
- Switch to a map view where photos are marked at their geo-coordinates.

### Flickr API Integration
- Fetches and displays trending and curated images from Flickr.
- Uses RESTful API calls with secure API key handling.

### Bottom Navigation Bar
- Effortlessly switch between Gallery and Map views.

### Coil Image Loading
- Fast, efficient image loading with in-memory caching.
- Placeholder images while loading and manual cache refresh.

### WebView Integration
- Tap on images to open the Flickr web page within the app.

### OSMDroid Map Customization
- Zoomable, scrollable map view using OSMDroid.  
- Custom photo markers replace standard pins.

### Marker Interaction
- Tap once to view the photo title in a popup.  
- Tap again to view the photo’s Flickr page.

### Dynamic Data Refresh
- Reload the photo gallery on-demand to fetch the latest images.

### MVVM Architecture
- Built with **MVVM (Model-View-ViewModel)** for scalability and clean code management.

---

![FancyGallery Demo](https://github.com/chhsch/FancyGallery/blob/6f8730f19994673ddb693db84fb1ff73e46386da/img/demo.gif)

---

### Tech Stack
- **Kotlin**  
- **Android Studio**  
- **Flickr API**  
- **Coil** for image loading  
- **OSMDroid** for interactive maps  
- **MVVM Architecture**  

---

### Getting Started

1. **Clone the Repository**
   ```bash
   git clone https://github.com/chhsch/FancyGallery-App.git
   ```

2. **Add Flickr API Key**
   - Create a file named `apikey.properties` in the root directory.
   - Add your API key:
     ```
     FLICKR_API_KEY="your_api_key_here"
     ```

3. **Run the App**
   - Open the project in **Android Studio**.  
   - Sync Gradle and run the app on a device/emulator.



