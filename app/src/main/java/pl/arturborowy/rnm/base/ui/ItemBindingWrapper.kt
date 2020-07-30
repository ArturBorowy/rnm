package pl.arturborowy.rnm.base.ui

import androidx.annotation.LayoutRes
import me.tatarka.bindingcollectionadapter2.ItemBinding

class ItemBindingWrapper {

    fun <ModelT> createBinding(
        variableId: Int,
        @LayoutRes layoutRes: Int,
        extra: Map<Int, Any> = emptyMap()
    ): ItemBinding<ModelT> {
        var itemBinding = ItemBinding.of<ModelT>(variableId, layoutRes)
        extra.forEach { (variableId, value) ->
            run {
                itemBinding = itemBinding.bindExtra(variableId, value)
            }
        }
        return itemBinding
    }
}
