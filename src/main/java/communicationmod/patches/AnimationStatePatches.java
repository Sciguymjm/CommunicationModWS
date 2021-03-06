package communicationmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import communicationmod.CommunicationMod;
import communicationmod.CommunicationMod;

// Keeps animations at normal speed
public class AnimationStatePatches {
	@SpirePatch(clz = com.esotericsoftware.spine.AnimationState.class, method = "update")
	public static class Update {
		@SpirePrefixPatch
		public static void Prefix(Object o, @ByRef(type = "float") float[] delta) {
			if (delta[0] == CommunicationMod.getMultDelta()) {
				delta[0] = CommunicationMod.getDelta();
			}
		}
	}
}
