package me.lofienjoyer.nublada.engine.events.world;

import me.lofienjoyer.nublada.engine.world.Chunk;

public class ChunkUpdateEvent extends ChunkEvent {

    public ChunkUpdateEvent(Chunk chunk) {
        super(chunk);
    }

}
