/*******************************************************************************
 * Copyright (c) 2021 Obeo.
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
package org.eclipse.sirius.web.graphql.datafetchers.representationmetadata;

import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.spring.graphql.QueryDataFetcher;
import org.eclipse.sirius.web.core.api.IRepresentationDescriptionSearchService;
import org.eclipse.sirius.web.representations.IRepresentationDescription;
import org.eclipse.sirius.web.representations.IRepresentationMetadata;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to retrieve the representation description from its metadata.
 * <p>
 * It will be used to fetch the data for the following GraphQL field:
 * </p>
 *
 * <pre>
 * type RepresentationMetadata {
 *   description: RepresentationDescription!
 * }
 * </pre>
 *
 * @author pcdavid
 */
@QueryDataFetcher(type = "RepresentationMetadata", field = "description")
public class RepresentationMetadataDescriptionDataFetcher implements IDataFetcherWithFieldCoordinates<IRepresentationDescription> {
    private final IRepresentationDescriptionSearchService representationDescriptionSearchService;

    public RepresentationMetadataDescriptionDataFetcher(IRepresentationDescriptionSearchService representationDescriptionSearchService) {
        this.representationDescriptionSearchService = Objects.requireNonNull(representationDescriptionSearchService);
    }

    @Override
    public IRepresentationDescription get(DataFetchingEnvironment environment) throws Exception {
        IRepresentationMetadata representationMetadata = environment.getSource();
        UUID descriptionId = representationMetadata.getDescriptionId();
        return this.representationDescriptionSearchService.findById(descriptionId).orElse(null);
    }

}
