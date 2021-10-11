package plus.misterplus.cindertally.helper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import plus.misterplus.cindertally.CinderTally;
import plus.misterplus.cindertally.CinderTallyConstants;

public class NBTHelper {


    private static CompoundNBT getPersistedData(PlayerEntity player, boolean createIfMissing) {
        CompoundNBT nbt = player.getPersistentData().getCompound(PlayerEntity.PERSISTED_NBT_TAG);
        if (createIfMissing) {
            player.getPersistentData().put(PlayerEntity.PERSISTED_NBT_TAG, nbt);
        }
        return nbt;
    }

    public static void initLifespan(PlayerEntity player) {
        // lifespan in in-game years, 70-100
        int lifespan = player.getCommandSenderWorld().random.nextInt(30) + 70;
        // nbt stored in ticks
        getPersistedData(player, true).putInt(CinderTallyConstants.LIFESPAN_NBT_TAG, lifespan * 365 * 20 * 60 * 20);
        CinderTally.LOGGER.debug("Init lifespan for player " + player.getName().getContents() + ": " + lifespan + " years.");
    }

    public static void diminishLifespan(PlayerEntity player) {
        getPersistedData(player, true).putInt(CinderTallyConstants.LIFESPAN_NBT_TAG, getPersistedData(player, false).getInt("cindertally_lifespan") - 1);
    }

    public static int getLifespan(PlayerEntity player) {
        return getPersistedData(player, false).getInt(CinderTallyConstants.LIFESPAN_NBT_TAG);
    }

    public static boolean isFirstLogin(PlayerEntity player) {
        if (!getPersistedData(player, false).getBoolean(CinderTallyConstants.NEW_NBT_TAG)) {
            getPersistedData(player, true).putBoolean(CinderTallyConstants.NEW_NBT_TAG, true);
            return true;
        }
        return false;
    }
}
