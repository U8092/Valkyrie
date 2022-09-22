package me.lofienjoyer.nublada.engine.graphics.mesh;

import me.lofienjoyer.nublada.engine.world.Chunk;

/**
 * Contains the solid and transparent meshes of a chunk
 */
public class MeshBundle {

    private Mesh[] solidMesh;
    private Mesh[] transparentMesh;

    private Mesher[] greedyMesher;
    private Mesher[] dynamicMesher;

    public MeshBundle(Chunk chunk) {
        this.greedyMesher = new GreedyMesher[2];
        this.greedyMesher[0] = new GreedyMesher(chunk, 1).compute();
        this.greedyMesher[1] = new GreedyMesher(chunk, 2).compute();

        this.dynamicMesher = new DynamicMesher[2];
        this.dynamicMesher[0] = new DynamicMesher(chunk, 1).compute();
        this.dynamicMesher[1] = new DynamicMesher(chunk, 2).compute();
    }

    public MeshBundle loadMeshToGpu() {
        solidMesh = new Mesh[2];
        solidMesh[0] = greedyMesher[0].loadToGpu();
        solidMesh[1] = greedyMesher[1].loadToGpu();

        transparentMesh = new Mesh[2];
        transparentMesh[0] = dynamicMesher[0].loadToGpu();
        transparentMesh[1] = dynamicMesher[1].loadToGpu();

        greedyMesher = null;
        dynamicMesher = null;
        return this;
    }

    public Mesh[] getSolidMeshes() {
        return solidMesh;
    }

    public Mesh[] getTransparentMeshes() {
        return transparentMesh;
    }
}
