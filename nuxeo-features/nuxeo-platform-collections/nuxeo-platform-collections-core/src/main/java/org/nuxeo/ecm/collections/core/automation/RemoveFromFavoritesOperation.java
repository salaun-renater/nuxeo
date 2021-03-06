/*
 * (C) Copyright 2016 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     <a href="mailto:grenard@nuxeo.com">Guillaume Renard</a>
 */
package org.nuxeo.ecm.collections.core.automation;

import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.collections.api.FavoritesManager;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;

/**
 * Class for the operation to remove a list of documents from the Favorites.
 *
 * @since 8.1
 */
@Operation(id = RemoveFromFavoritesOperation.ID, category = Constants.CAT_DOCUMENT, label = "Remove from favorites", description = "Remove a list of documents from the favorites. "
        + "No value is returned.", aliases = { "Collection.RemoveFromFavorites" })
public class RemoveFromFavoritesOperation {
    public static final String ID = "Document.RemoveFromFavorites";

    @Context
    protected CoreSession session;

    @Context
    protected FavoritesManager favoritesManager;

    @OperationMethod
    public DocumentModelList run(DocumentModelList docs) {
        for (DocumentModel doc : docs) {
            favoritesManager.removeFromFavorites(doc, session);
        }

        return docs;
    }

    @OperationMethod
    public DocumentModel run(DocumentModel doc) {
        favoritesManager.removeFromFavorites(doc, session);

        return doc;
    }
}
