package org.rust.lang.module

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.StdModuleTypes
import com.intellij.openapi.projectRoots.SdkTypeId
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import org.rust.lang.icons.RustIcons
import javax.swing.Icon

/**
 * @author Evgeny.Kurbatsky
 */
public class RustModuleBuilder() : ModuleBuilder() {

    override fun getBuilderId() = "rust.module.builder"

    override fun modifySettingsStep(settingsStep: SettingsStep): ModuleWizardStep? =
        StdModuleTypes.JAVA!!.modifySettingsStep(settingsStep, this)

    override fun getBigIcon(): Icon = RustIcons.BIG

    override fun getGroupName(): String? = "Rust"

    override fun getPresentableName(): String? = "Rust"

    override fun createWizardSteps(wizardContext: WizardContext, modulesProvider: ModulesProvider): Array<ModuleWizardStep> =
        moduleType.createWizardSteps(wizardContext, this, modulesProvider)

    override fun getModuleType(): RustModuleType {
        return RustModuleType.INSTANCE
    }

    override fun setupRootModel(rootModel: ModifiableRootModel?) {
        if (myJdk != null) {
            rootModel!!.sdk = myJdk
        } else {
            rootModel!!.inheritSdk()
        }

        doAddContentEntry(rootModel)
    }

    override fun isSuitableSdkType(sdkType: SdkTypeId?): Boolean {
        return true
    }
}
