package communicationmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class CardCrawlGamePatches {
	@SpirePatch(clz = com.megacrit.cardcrawl.core.CardCrawlGame.class, method = "render")
	public static class DeltaPatch {
		public static ExprEditor Instrument() {
			return new ExprEditor() {
				@Override
				public void edit(MethodCall m) throws CannotCompileException {
					if (m.getMethodName().equals("getRawDeltaTime")) {
						m.replace("{ $_ = communicationmod.CommunicationMod.getDelta(); }");
					}
				}
			};
		}
	}
}
