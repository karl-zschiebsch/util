# util

Util contains useful classes for server-side game projects.

## content
- [beans](#beans)
- [collection](#collection)
- [entity](#entity)
- [value](#value)

### beans
Contains the core interface `Observable`. You can add listeners to each observable. When the value of an observable changes, a handler calls all listeners.

### collection
The collection package contains various observable collection types, such as `ObservableList` or `ObservableMap`.

### entity
This package contains the `Entity` interface. Each entity has a unique ID and can be updated over time. A parent is a special entity that can contain and update multiple entities.

### value
The value package contains `ObservableValue` and subclasses.