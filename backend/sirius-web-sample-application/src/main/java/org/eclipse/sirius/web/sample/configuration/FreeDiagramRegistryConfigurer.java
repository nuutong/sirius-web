/*******************************************************************************
 * Copyright (c) 2019, 2020 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.web.sample.configuration;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.sirius.web.api.configuration.IRepresentationDescriptionRegistry;
import org.eclipse.sirius.web.api.configuration.IRepresentationDescriptionRegistryConfigurer;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription;
import org.eclipse.sirius.web.freediagram.flow.DiagramDescriptionSupplier;
import org.eclipse.sirius.web.services.api.objects.IEditService;
import org.eclipse.sirius.web.services.api.objects.IObjectService;
import org.springframework.context.annotation.Configuration;

/**
 * Used to register the free diagram contribution to the registry.
 *
 * @author hmarchadour
 */
@Configuration
public class FreeDiagramRegistryConfigurer implements IRepresentationDescriptionRegistryConfigurer {

    private final Supplier<DiagramDescription> freeDiagramDescriptionSupplier;

    public FreeDiagramRegistryConfigurer(IObjectService objectService, IEditService editService) {
        this.freeDiagramDescriptionSupplier = new DiagramDescriptionSupplier(Objects.requireNonNull(objectService), Objects.requireNonNull(editService));
    }

    @Override
    public void addRepresentationDescriptions(IRepresentationDescriptionRegistry registry) {
        registry.add(this.freeDiagramDescriptionSupplier.get());
    }

}
