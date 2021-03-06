package org.rust.lang.core.resolve.scope

import com.intellij.psi.PsiElement
import org.rust.lang.core.psi.RustCompositeElement
import org.rust.lang.core.psi.RustNamedElement
import org.rust.lang.core.psi.RustPatVar
import org.rust.lang.core.resolve.RustResolveEngine

public interface RustResolveScope : RustCompositeElement {
    fun listDeclarations(before: PsiElement): List<RustPatVar>
}

//
// Extension points
//

internal fun RustResolveScope.resolveWith(v: RustResolveEngine.ResolveScopeVisitor): RustNamedElement? {
    this.accept(v)
    return v.matched
}
