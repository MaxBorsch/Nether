/*
 * Copyright 2014 MovingBlocks
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

import org.terasology.core.world.generator.facetProviders.FlatSurfaceHeightProvider;
import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

@RegisterWorldGenerator(id = "underworld", displayName = "Underworld")
public class UnderworldWorldGenerator extends BaseFacetedWorldGenerator {

    public UnderworldWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    @Override
    protected WorldBuilder createWorld() {
        int seaLevel = -3;

        return new WorldBuilder(worldGeneratorPluginLibrary)
                .setSeaLevel(seaLevel)
                .addProvider(new UnderworldDepthProvider(0))
                .addProvider(new UnderworldSurfaceProvider())
                .addProvider(new FlatSurfaceHeightProvider(0))
                .addProvider(new SeaLevelProvider(seaLevel))
                .addProvider(new MountainsProvider())
                .addProvider(new ConstantHumidityProvider(0.1f))
                .addProvider(new ConstantSurfaceTemperatureProvider(0.9f))
                .addRasterizer(new UnderworldWorldRasterizer())
                .addPlugins();
    }
}
