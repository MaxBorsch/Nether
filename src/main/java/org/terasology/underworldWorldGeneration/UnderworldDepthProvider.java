/*
 * Copyright 2015 MovingBlocks
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

import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Produces(UnderworldDepthFacet.class)
public class UnderworldDepthProvider implements FacetProviderPlugin {

    private int depth = -257;

    public UnderworldDepthProvider (int depth) {
        this.depth = depth;
    }

    public UnderworldDepthProvider () {}

    @Override
    public void process(GeneratingRegion region) {
        region.setRegionFacet(UnderworldDepthFacet.class, new UnderworldDepthFacet(depth));
    }
}
