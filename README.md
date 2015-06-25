RELEASE NOTES:

Dependencies: common

1.2.5  - Added getChildren to XmlTag

1.2.4a - Bug fix to parsing input tags with > inside of attributes.

1.2.4  - Always set validation flag based on validate parameter.

1.2.3a - Added setValidateXml to XmlTag.

1.2.3  - Added root line setting for xml tags that are the root tag of a document.
       - Fix in formatting of form tag to allow non-html

1.2.2  - Made so that javascript bodies can contain parsable tags.
       - Bug fix to HtmlMarkupFactory where parseText was not being extended properly
         from MarkupFactory.
       - Added trimChildText method that forces all text elements to be trimmed when
         not in preserveWhiteSpace mode except for pre, xmp, and textarea tags.

1.2.1  - Added com.zitego.markup.html.javascript.JavascriptFormatter.

1.2    - Changed to allow optional preserve white space so that markup content that is
         parsed is kept exactly the same as it was (no indentation formatting)
       - Changed MarkupFactory to pre-pend the leading spaces to text content when
         parsing if preserveWhiteSpace is set to true.
       - Changed MarkupContent.removeLeadingSpaces to return a StringBuffer of the
         spaces that were removed.
       - Changed many classes to allow for preserving whitespace if that option is
         turned on.

1.1.1b - Changed to remove event handlers on null set.
       - Added JavascriptBody for parsing content inside of script tags and functions.
       - Changed so that text effect tags do not have a new line after the end tag by default.
         Also made them all be on own line by default.
       - Made it so that a tag with /> at the end of the start tag will cause the setHasEndTag
         method to be called with false in setAttributes(StringBuffer)
       - Changed so that text is added with spaces after it (i.e. they are not stripped).
       - Fixed bug in Header class to remove tag name in validateTagName.
       - Fixed bug in js event handlers to allow them to contain markup content.
       - Changed option tag to not have an end tag.
       - Bug fix to handle null tag attribute values.
       - Bug fix in function and statement parsing.
       - Bug fix in html parser for input tags with invalid type.

1.1.1  - Fixed a bug in XmlTag.getValue to return the properly return content of a CDATASection.
       - Changed TagAttribute to preserve case.
       - Added getStartTagNewline to MarkupTag
       - Allowed for tag attributes to contain markup content

1.1    - Changed HtmlMarkupFactory to have createTagClass method that can be overridden.
       - Added createHtmlMarkupFactory to HtmlMarkupFactory that can be used to load extensions
         of the HtmlMarkupFactory.
       - Bug fix in setHref in Anchor tag when base path and href are =.

1.0.1e - Added search methods to MarkupContent to return all tags with the given class or tag name.
       - Bug fix in Form to add elements when parsing.

1.0.1d - Moved base href to the beginning of the head tag.
       - Made onSubmit and onReset public methods in Form.
       - Added XmlProperties in com.zitego.markup.xml
       - Added url, file, and input stream constructors to CascadingStyleSheet
       - Bug fix in html to set body, head, and frameset after parse.

1.0.1c - Parsing of the head tag with script tags caused errors. This is fixed.
       - Made less strict in parsing.
       - Forced CommentTag to return !-- as the tag name.

1.0.1b - Bug fix for no input type specification in HtmlMarkupFactory.getTagName()
       - Changed Paragraph, UnknownTag, and UnknownHtmlTag to not have an end tag.
       - Changed so that most MarkupContent does not need a parent.
       - Fixed parsing inline style declarations that are not enclosed by quotes and
         is not terminated by a semi-colon.
       - Bug fix for closing end tags in MarkupFactory.

1.0.1a - Updated to use newest common jar.
       - Fixed bug in Html parse method where head, body, and frameset were not being set.
       - Changed class Link to be set as having no end tag.
       - Added style tag body class to handle parsing style blocks.

1.0.1  - Added ability to set JSEventHandlers by an AttributeList in MarkupTag.
       - Added ability to supply a list of methods to call on an object in
         FormatTag.
       - Changed the parse method in the MarkupConverter interface to parse
         Objects as well as Strings. Changed all implementing classes.
       - Added multiple com.zitego.markup.xml classes for parsing xml

1.0    - Initial Release
