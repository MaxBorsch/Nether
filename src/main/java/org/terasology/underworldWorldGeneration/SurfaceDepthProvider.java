/*
 * Copyright 2016 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.underworldWorldGeneration;

import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Rect2i;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.facets.SurfaceDepthFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Produces(SurfaceDepthFacet.class)
public class SurfaceDepthProvider implements FacetProviderPlugin {

    private int depth = -256;

    public SurfaceDepthProvider(int depth) {
        this.depth = depth;
    }

    public SurfaceDepthProvider() {}

    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(SurfaceDepthFacet.class);
        SurfaceDepthFacet facet = new SurfaceDepthFacet(region.getRegion(), border);

        Rect2i processRegion = facet.getWorldRegion();
        for (BaseVector2i position : processRegion.contents()) {
            facet.setWorld(position, depth);
        }

        region.setRegionFacet(SurfaceDepthFacet.class, facet);
    }
}
