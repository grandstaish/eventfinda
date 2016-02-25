package nz.bradcampbell.eventfinda.shared.activity

import android.content.Context
import android.support.v4.view.LayoutInflaterFactory
import android.util.AttributeSet
import android.view.View

import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.util.HashMap
import java.util.HashSet

/**
 * This layout inflater will inflate any view with the constructor signature as follows:
 * (Context, AttributeSet, MainActivityComponent)
 */
class MainActivityComponentViewFactory(private val component: MainActivityComponent) : LayoutInflaterFactory {

  override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
    var result: View? = null

    if (name.startsWith(BASE_APP_PACKAGE)) {
      if (!namesVisited.contains(name)) {
        namesVisited.add(name)
        try {
          // Class not found in the cache, see if it's real, and try to add it
          val clazz = context.classLoader.loadClass(name).asSubclass(View::class.java)
          constructorMap.put(name, clazz.getConstructor(*constructorSignature))
        } catch (e: ClassNotFoundException) {
          // This will get picked up by the system later. No need to handle here.
        } catch (e: NoSuchMethodException) {
        }
      }

      val constructor = constructorMap[name]
      if (constructor != null) {
        try {
          constructor.isAccessible = true
          result = constructor.newInstance(context, attrs, component)
        } catch (e: InvocationTargetException) {
          throw RuntimeException(e)
        } catch (e: InstantiationException) {
          throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
          throw RuntimeException(e)
        }
      }
    }

    if (result == null) {
      // Try to let the Activity handle it (inflating fragments from XML)
      result = component.activity().onCreateView(name, context, attrs)
    }

    if (result == null) {
      // Get themed views from app compat
      result = component.appCompatDelegate().createView(parent, name, context, attrs)
    }

    return result
  }

  companion object {
    private val BASE_APP_PACKAGE = "nz.bradcampbell.eventfinda"

    private val namesVisited = HashSet<String>()
    private val constructorMap = HashMap<String, Constructor<out View>>()
    private val constructorSignature = arrayOf(Context::class.java, AttributeSet::class.java, MainActivityComponent::class.java)
  }
}
