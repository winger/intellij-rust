package org.rust.lang.core.resolve.util

import com.intellij.psi.PsiElement
import org.rust.lang.core.resolve.scope.RustResolveScope

public object RustResolveUtil {

    fun getResolveScope(elem: PsiElement, mayReturnItself: Boolean = false): RustResolveScope? {
        var current = if (mayReturnItself) elem else elem.parent
        while (current != null) {
            when (current) {
                is RustResolveScope -> return current
                else -> current = current.parent
            }
        }

        return null
    }

}

