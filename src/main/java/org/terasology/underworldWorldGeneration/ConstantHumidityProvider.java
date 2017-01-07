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

import org.terasology.math.TeraMath;
import org.terasology.utilities.procedural.SubSampledNoise;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.facets.SurfaceHumidityFacet;

/**
 * Defines surface humidity in the range [0..1] based on random noise.
 */
@Produces(SurfaceHumidityFacet.class)
public class ConstantHumidityProvider implements FacetProvider {
    private static final int SAMPLE_RATE = 4;

    private SubSampledNoise humidityNoise;

    private float humidityAverage;

    private long seed;

    public ConstantHumidityProvider(float humidityAverage) {
        this.humidityAverage = humidityAverage;
    }

    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(SurfaceHumidityFacet.class);
        SurfaceHumidityFacet facet = new SurfaceHumidityFacet(region.getRegion(), border);

        float[] noise = humidityNoise.noise(facet.getWorldRegion());
        for (int i = 0; i < noise.length; ++i) {
            noise[i] = TeraMath.clamp(humidityAverage + (noise[i] * 0.1f - 0.05f));
        }
        facet.set(noise);
        region.setRegionFacet(SurfaceHumidityFacet.class, facet);
    }
}
